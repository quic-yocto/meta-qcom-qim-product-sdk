SUMMARY = "Qualcomm QIM Product SDK package groups"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

PROVIDES = "${PACKAGES}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

SRC_URI += "file://install.sh"

PACKAGES = "${PN}"

RDEPENDS:${PN} = " \
    packagegroup-qcom-gst \
    packagegroup-qcom-ml \
  "
