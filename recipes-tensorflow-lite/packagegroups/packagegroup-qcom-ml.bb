SUMMARY = "QCOM Machine Learning package groups"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = " \
    ${PN} \
    ${PN}-tflite \
    "

RDEPENDS:${PN} = " \
    ${PN}-tflite \
    libgomp-dev \
    qnn \
    snpe \
    "

RDEPENDS:${PN}-tflite = " \
    tensorflow-lite \
    "
