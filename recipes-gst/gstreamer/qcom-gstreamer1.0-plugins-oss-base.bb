inherit cmake pkgconfig gobject-introspection

SUMMARY = "Qualcomm open-source GStreamer base"
SECTION = "multimedia"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "gstreamer1.0-plugins-base"
DEPENDS:append:qcom-custom-bsp = " qcom-fastcv-binaries"
DEPENDS += "opencv"
DEPENDS += "virtual/kernel"
DEPENDS += "virtual/egl"
DEPENDS += "virtual/libgles2"
DEPENDS += "json-glib"
DEPENDS += "python3-pygobject"
DEPENDS += "qcom-camera-server"

RDEPENDS:${PN} += "opencv"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/gst-plugins-qti-oss.git;protocol=https"
SRCBRANCH  = "imsdk.lnx.2.0.0.r2-rel"
SRCREV     = "7afe3eeb1552b3892fb763d3026aa98a4b908826"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};subpath=gst-plugin-base"
S = "${WORKDIR}/gst-plugin-base"

# Install directries.
INSTALL_INCDIR := "${includedir}"
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"
INSTALL_DATADIR := "${datadir}"

EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.20.7"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DSYSROOT_BINDIR=${STAGING_BINDIR}"
EXTRA_OECMAKE += "-DPYTHON_SITEPACKAGES_DIR=${PYTHON_SITEPACKAGES_DIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_INCDIR=${INSTALL_INCDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_BINDIR=${INSTALL_BINDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=${INSTALL_LIBDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_DATADIR=${INSTALL_DATADIR}"

PACKAGECONFIG = "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/libgbm', 'gbm', 'gbm', '', d)} "
PACKAGECONFIG[gbm] = " , ,gbm,gbm"

FILES:${PN} += "${INSTALL_BINDIR}"
FILES:${PN} += "${INSTALL_LIBDIR}"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""

INSANE_SKIP:${PN} = "dev-so"
