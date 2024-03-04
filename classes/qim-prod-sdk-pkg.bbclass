# Copyright (c) 2024 Qualcomm Innovation Center, Inc. All rights reserved.
# SPDX-License-Identifier: BSD-3-Clause-Clear
SSTATETASKS += "do_generate_qim_prod_sdk "
SSTATE_OUT_DIR = "${DEPLOY_DIR}/qim_prod_sdk_artifacts/"
SSTATE_IN_DIR = "${TOPDIR}/${SDK_PN}"
TMP_SSTATE_IN_DIR = "${TOPDIR}/${SDK_PN}_tmp"

# We only need the packaging tasks - disable the rest
do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_populate_lic[noexec] = "1"
do_package_qa[noexec] = "1"

LICENSE = "BSD-3-Clause-Clear"
INSANE_SKIP:${PN} += "already-stripped"
ALLOW_EMPTY:${PN} = "1"

python __anonymous () {
    package_type = d.getVar("IMAGE_PKGTYPE", True)
    if package_type == "ipk":
        bb.build.addtask('do_generate_qim_prod_sdk', 'do_package_write_ipk', 'do_packagedata', d)
}

addtask do_generate_qim_prod_sdk_setscene
do_generate_qim_prod_sdk[sstate-inputdirs] = "${SSTATE_IN_DIR}"
do_generate_qim_prod_sdk[sstate-outputdirs] = "${SSTATE_OUT_DIR}"
do_generate_qim_prod_sdk[dirs] = "${SSTATE_IN_DIR} ${SSTATE_OUT_DIR}"
do_generate_qim_prod_sdk[cleandirs] = "${SSTATE_IN_DIR} ${SSTATE_OUT_DIR}"
do_generate_qim_prod_sdk[stamp-extra-info] = "${MACHINE_ARCH}"
do_generate_qim_prod_sdk[depends] = " \
         snpe:do_packagedata \
         qnn:do_packagedata \
         gstreamer1.0-plugins-qcom-oss-mlsnpe:do_packagedata \
         gstreamer1.0-plugins-qcom-oss-mlqnn:do_packagedata \
         gstreamer1.0-plugins-qcom-oss-mltflite:do_packagedata \
         packagegroup-qcom-qim-product:do_packagedata \
         qim-sdk:do_generate_qim_sdk \
         tflite-sdk:do_generate_tflite_sdk \
   "
# Add a task to generate qim product sdk
do_generate_qim_prod_sdk () {
    # generate QIM PRODUCT SDK package
    if [ ! -d ${TMP_SSTATE_IN_DIR}/${SDK_PN} ]; then
        mkdir -p ${TMP_SSTATE_IN_DIR}/${SDK_PN}/
    fi
    cd ${TMP_SSTATE_IN_DIR}/
    tar -xvf ${DEPLOY_DIR}/qimsdk_artifacts/qim-sdk_*.tar.gz .
    tar -xvf ${DEPLOY_DIR}/tflitesdk_artifacts/tflite-sdk_*.tar.gz .
    for pkg in `find . -type f  -name "*.ipk"`
    do
        mv $pkg ./${SDK_PN}/
    done
    cp -r ${WORKDIR}/*install.sh ${TMP_SSTATE_IN_DIR}/${SDK_PN}/
    PKG_LISTS="${@get_pkgs_list(d)}"
    for pkg in "${PKG_LISTS}"
    do
        cp ${pkg} ${TMP_SSTATE_IN_DIR}/${SDK_PN}/
    done
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
    mkdir -p ./${SDK_PN}/doc/
    for f in `find . -type f \( -name "*-doc_*" -o -name "*-staticdev_*" \)`
    do
        mv $f ./${SDK_PN}/doc/
    done
    rm -rf ./${SDK_PN}/doc
    tar -zcf ${SSTATE_IN_DIR}/${SDK_PN}-rel_${PV}.tar.gz ./${SDK_PN}/*
    rm -rf ${TMP_SSTATE_IN_DIR}
    bbwarn "QIM Product SDK available at ${SSTATE_OUT_DIR}"
}

def get_pkgs_list(d):
  import os
  pkgtype = d.getVar("IMAGE_PKGTYPE", True)
  deploydir = d.getVar("DEPLOY_DIR", True)
  pkgslist = []
  for _, pkgdirs, _ in os.walk(os.path.join(deploydir, pkgtype)):
    for pkgdir in pkgdirs:
      for f in os.listdir(os.path.join(deploydir, pkgtype, pkgdir)):
        if "qnn" in os.path.basename(f) or "snpe" in os.path.basename(f):
          pkgslist.append(os.path.join(deploydir, pkgtype, pkgdir, f))
  return " \\\n ".join(pkgslist)

python do_generate_qim_sdk_setscene() {
    sstate_setscene(d)
}

do_cleanall[depends] = "qim-sdk:do_cleanall tflite-sdk:do_cleanall"

RM_WORK_EXCLUDE += "${PN}"
