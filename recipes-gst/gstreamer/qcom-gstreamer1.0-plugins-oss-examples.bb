inherit cmake pkgconfig

SUMMARY = "Generic examples for GStreamer pipelines."
SECTION = "multimedia"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

# Dependencies.
DEPENDS := "gstreamer1.0"
DEPENDS += "binder"
DEPENDS += "qcom-gstreamer1.0-plugins-oss-base"
DEPENDS += "securemsm"
DEPENDS += "media-headers"
DEPENDS += "libutils"
DEPENDS += "qmmf-sdk"
DEPENDS += "gstreamer1.0-plugins-bad"
DEPENDS += "json-glib"
DEPENDS += "libsoup-2.4"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/gst-plugins-qti-oss.git;protocol=https"
SRCBRANCH  = "imsdk.lnx.2.0.0.r2-rel"
SRCREV     = "7afe3eeb1552b3892fb763d3026aa98a4b908826"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};subpath=gst-plugin-examples"
S = "${WORKDIR}/gst-plugin-examples"

# Install directries.
INSTALL_BINDIR := "${bindir}"
INSTALL_LIBDIR := "${libdir}"

PACKAGECONFIG[auto-framing] = "-DENABLE_TRACKING_CAM=true, -DENABLE_TRACKING_CAM=false, qti-auto-framing-stabilization, qti-auto-framing-stabilization"

# WebRTC enable
ENABLE_WEBRTC := "TRUE"

EXTRA_OECMAKE += "-DGST_VERSION_REQUIRED=1.20.7"
EXTRA_OECMAKE += "-DSYSROOT_INCDIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DSYSROOT_LIBDIR=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_BINDIR=${INSTALL_BINDIR}"
EXTRA_OECMAKE += "-DGST_PLUGINS_QTI_OSS_INSTALL_LIBDIR=${INSTALL_LIBDIR}"
EXTRA_OECMAKE += "-DCAMERA_CLIENT_DISABLED=${CAMERA_CLIENT_DISABLED}"
EXTRA_OECMAKE += "-DCODEC2_ENCODE=${CODEC2_ENCODE}"
EXTRA_OECMAKE += "-DENABLE_WEBRTC=${ENABLE_WEBRTC}"

FILES:${PN} += "${INSTALL_BINDIR}"
FILES:${PN} += "${INSTALL_LIBDIR}"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
