#!/bin/bash

# Copyright (c) 2024 Qualcomm Innovation Center, Inc. All rights reserved.
# SPDX-License-Identifier: BSD-3-Clause-Clear

SDK_NAME="TFLITE_SDK"

FOUND_PKGS=""
PKG_LIST_DIR="/opt/qcom/tflite/"
PKG_LIST_FILE="${PKG_LIST_DIR}/${SDK_NAME}.list"

# check permission for execute this script
function check_permission() {
    if [ "$(whoami)" != "root" ]; then
        echo "ERROR: need root permission"
        exit 1
    fi
}

# scan packages in current path
function scan_tflite_packages() {
    FOUND_PKGS=`find . -name "*.ipk" \
        | grep -v "\-dbg_" \
        | grep -v "\-dev_" \
        | grep -v "\-staticdev_" \
        | tr '\n' ' '`
}

# install packages and save list to file
function install_tflite_packages() {
    install_command="opkg install --force-reinstall --force-depends --force-overwrite"

    for PKG_FILE in ${FOUND_PKGS}; do
        ${install_command} ${PKG_FILE}
    done

    mkdir -p "${PKG_LIST_DIR}"

    rm -f "${PKG_LIST_FILE}"

    for pkg in ${FOUND_PKGS}; do
        pkg_name=`echo $pkg | awk -F'/' '{print $NF}' | awk -F'_' '{print $1}'`
        echo ${pkg_name} >> ${PKG_LIST_FILE}
    done
}

function main() {

    echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
    echo ">>> Install scripts for ${SDK_NAME}"
    echo "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"
    echo

    check_permission

    if [ -f ${PKG_LIST_FILE} ]; then
        printf "WARN: ${SDK_NAME} has installed, "
        while true; do
            read -p "Do you wish to install anyway? (Y/N)" yn
            case $yn in
                [Yy]* ) break;;
                [Nn]* ) exit 1;;
                * ) echo "Please answer yes or no.";;
            esac
        done
    fi

    scan_tflite_packages

    install_tflite_packages

}

main "$@"

