inherit cmake pkgconfig

SUMMARY = "QCOM Message Broker Clients"
HOMEPAGE = "https://git.codelinaro.org"
SECTION = "multimedia"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "mosquitto"
DEPENDS += "librdkafka"

RDEPENDS:${PN}:append = " librdkafka"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/gst-plugins-qti-oss.git;protocol=https"
SRCBRANCH  = "imsdk.lnx.2.0.0.r2-rel"
SRCREV     = "7afe3eeb1552b3892fb763d3026aa98a4b908826"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};subpath=gst-plugin-msgbroker"
S = "${WORKDIR}/gst-plugin-msgbroker"

# Install directries.
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"

EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.14.4"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_BINDIR=${INSTALL_BINDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=${INSTALL_LIBDIR}"

EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_VERSION=${PV}"

FILES:${PN} += "${INSTALL_LIBDIR}/gstreamer-1.0/*.so"
