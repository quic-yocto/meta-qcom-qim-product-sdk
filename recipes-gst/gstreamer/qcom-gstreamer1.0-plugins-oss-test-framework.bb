inherit cmake pkgconfig

SUMMARY = "Qualcomm open-source automation test framework for GStreamer pipelines."
SECTION = "multimedia"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "gstreamer1.0-plugins-base"
DEPENDS += "qcom-gstreamer1.0-plugins-oss-base"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/gst-plugins-qti-oss.git;protocol=https"
SRCBRANCH  = "imsdk.lnx.2.0.0.r2-rel"
SRCREV     = "7afe3eeb1552b3892fb763d3026aa98a4b908826"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};subpath=gst-test-framework"
S = "${WORKDIR}/gst-test-framework"

# Install directries.
INSTALL_INCDIR := "${includedir}"
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"

# Encode-related variables
ENABLE_VIDEO_ENCODE := "TRUE"

# Decode-related variables
ENABLE_VIDEO_DECODE := "TRUE"

# Display-related variables
ENABLE_DISPLAY := "TRUE"

# ML-related variables
ENABLE_ML := "TRUE"

# Camera-related variables
ENABLE_AUDIO := "TRUE"

EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.20.7"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_BINDIR=${INSTALL_BINDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=${INSTALL_LIBDIR}"
EXTRA_OECMAKE += "-DENABLE_VIDEO_ENCODE=${ENABLE_VIDEO_ENCODE}"
EXTRA_OECMAKE += "-DENABLE_VIDEO_DECODE=${ENABLE_VIDEO_DECODE}"
EXTRA_OECMAKE += "-DENABLE_DISPLAY=${ENABLE_DISPLAY}"
EXTRA_OECMAKE += "-DENABLE_ML=${ENABLE_ML}"
EXTRA_OECMAKE += "-DENABLE_AUDIO=${ENABLE_AUDIO}"

FILES:${PN} += "${INSTALL_BINDIR}"
FILES:${PN} += "${INSTALL_LIBDIR}"

FILES:${PN}-dbg += "${INSTALL_LIBDIR}/gstreamer-1.0/.debug"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
