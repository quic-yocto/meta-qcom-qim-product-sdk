inherit autotools pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}/${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

SUMMARY          = "QNN-SDK"
DESCRIPTION      = "Qualcomm Neural Network SDK"

QNN_DIR = "${DL_DIR}/qnn"

do_fetch() {
    if [ ! -f "/usr/bin/qpm-cli" ]; then
        echo "QPM is not installed on host machine, Please try after QPM installation!!"
        exit 1
    fi
    if [ ! -d ${QNN_DIR}/lib ]; then
        mkdir -p ${QNN_DIR}
        /usr/bin/qpm-cli --license-activate qualcomm_ai_engine_direct
        yes y | /usr/bin/qpm-cli --extract qualcomm_ai_engine_direct --version ${QNN_VERSION} --path ${QNN_DIR}
    fi
}

def platform_dir(d):
    gccversion  = d.getVar("GCCVERSION", True).strip('%').split('.')[0]
    gccversion_integer = int(gccversion)
    sdk_lib_dir = d.getVar("QNN_DIR", True) + "/lib/"
    dir_prefix = "aarch64-oe-linux-gcc"
    for version in range (gccversion_integer, 8, -1):
        gccversion = str(version)
        pf_dir = dir_prefix + gccversion
        if os.path.exists(sdk_lib_dir) and os.path.isdir(sdk_lib_dir):
            for folder in os.listdir(sdk_lib_dir):
                if folder.startswith(pf_dir):
                    pf_dir += "*"
                    return pf_dir

PLATFORM_DIR = "${@platform_dir(d)}"

# Add Hexagon version for other targets below
HEXAGON_DIR:qcm6490 = "hexagon-v68"

do_compile[noexec] = "1"
do_package_qa[noexec] = "1"

do_install() {
    install -d ${D}/${bindir}
    install -d ${D}/${includedir}
    install -d ${D}/${libdir}/rfsa/adsp

    install -m 0755 ${QNN_DIR}/lib/${PLATFORM_DIR}/* ${D}/${libdir}
    install -m 0755 ${QNN_DIR}/bin/${PLATFORM_DIR}/* ${D}/${bindir}
    install -m 0755 ${QNN_DIR}/lib/${HEXAGON_DIR}/unsigned/* ${D}/${libdir}/rfsa/adsp

    cp -r ${QNN_DIR}/include/QNN/* ${D}/${includedir}
    chmod -R 0755 ${D}/${includedir}
    # libCalculator_skel.so is included by SNPE as well so we are removing from QNN
    # This fix will be present until we get proper resolution from MLG team
    rm -f ${D}/${libdir}/rfsa/adsp/libCalculator_skel.so
}

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

INSANE_SKIP_${PN} += "arch"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

FILES:${PN} += "${libdir}/*"
FILES:${PN} += "${bindir}/*"
FILES:${PN}-dev += "${includedir}/*"
