# Copyright (c) 2024 Qualcomm Innovation Center, Inc. All rights reserved.
# SPDX-License-Identifier: BSD-3-Clause-Clear

SSTATETASKS += "do_generate_tflite_sdk "
SSTATE_OUT_DIR = "${DEPLOY_DIR}/tflitesdk_artifacts/"
SSTATE_IN_DIR = "${TOPDIR}/${SDK_PN}"
TMP_SSTATE_IN_DIR = "${TOPDIR}/${SDK_PN}_tmp"

python __anonymous () {
    package_type = d.getVar("IMAGE_PKGTYPE", True)
    if package_type == "ipk":
        bb.build.addtask('do_generate_tflite_sdk', 'do_package_write_ipk', 'do_packagedata' , d)
}

addtask do_generate_tflite_sdk_setscene
do_generate_tflite_sdk[sstate-inputdirs] = "${SSTATE_IN_DIR}"
do_generate_tflite_sdk[sstate-outputdirs] = "${SSTATE_OUT_DIR}"
do_generate_tflite_sdk[dirs] = "${SSTATE_IN_DIR} ${SSTATE_OUT_DIR}"
do_generate_tflite_sdk[cleandirs] = "${SSTATE_IN_DIR} ${SSTATE_OUT_DIR}"
do_generate_tflite_sdk[stamp-extra-info] = "${MACHINE_ARCH}"
do_generate_tflite_sdk[depends] = " \
    tensorflow-lite:do_packagedata \
  "

# Add a task to generate Tflite sdk
do_generate_tflite_sdk () {
    # generate Tflite SDK package
    if [ ! -d ${TMP_SSTATE_IN_DIR}/${SDK_PN} ]; then
        mkdir -p ${TMP_SSTATE_IN_DIR}/${SDK_PN}/
    fi
    cp -r ${WORKDIR}/*install.sh ${TMP_SSTATE_IN_DIR}/${SDK_PN}/
    PKG_LISTS="${@get_pkgs_list(d)}"
    for pkg in "${PKG_LISTS}"
    do
        cp ${pkg} ${TMP_SSTATE_IN_DIR}/${SDK_PN}/
    done

    cd ${TMP_SSTATE_IN_DIR}
    tar -zcf ${SSTATE_IN_DIR}/${SDK_PN}_${PV}.tar.gz ./${SDK_PN}/*
    mkdir -p ./${SDK_PN}/dev/
    for f in `find . -type f \( -name "*-dev_*" \)`
    do
        mv $f ./${SDK_PN}/dev/
    done
    tar -zcf ${SSTATE_IN_DIR}/${SDK_PN}-dev_${PV}.tar.gz ./${SDK_PN}/dev/*
    rm -rf ./${SDK_PN}/dev
    mkdir -p ./${SDK_PN}/dbg/
    for f in `find . -type f \( -name "*-dbg_*" \)`
    do
        mv $f ./${SDK_PN}/dbg/
    done
    tar -zcf ${SSTATE_IN_DIR}/${SDK_PN}-dbg_${PV}.tar.gz ./${SDK_PN}/dbg/*
    rm -rf ./${SDK_PN}/dbg
    for f in `find . -type f \( -name "*-doc_*" -o -name "*-staticdev_*" -o -name "*-locale-*" \)`
    do
        rm -rf $f
    done
    tar -zcf ${SSTATE_IN_DIR}/${SDK_PN}-rel_${PV}.tar.gz ./${SDK_PN}/*
    rm -rf ${TMP_SSTATE_IN_DIR}
}

def get_pkgs_list(d):
    import os
    pkgtype = d.getVar("IMAGE_PKGTYPE", True)
    deploydir = d.getVar("DEPLOY_DIR", True)
    pkgslist = []
    for _, pkgdirs, _ in os.walk(os.path.join(deploydir, pkgtype)):
        for pkgdir in pkgdirs:
            for f in os.listdir(os.path.join(deploydir, pkgtype, pkgdir)):
                if "libgomp-dev" in os.path.basename(f) or "tensorflow-lite" in os.path.basename(f) :
                    pkgslist.append(os.path.join(deploydir, pkgtype, pkgdir, f))
    return " \\\n ".join(pkgslist)

python do_generate_tflite_sdk_setscene() {
    sstate_setscene(d)
}

RM_WORK_EXCLUDE += "${PN}"
