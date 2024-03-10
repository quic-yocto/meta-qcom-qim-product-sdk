SUMMARY = "Qualcomm Gstreamer package groups"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

PROVIDES = "${PACKAGES}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGES = " \
    ${PN}-dependencies \
    ${PN} \
    "

RDEPENDS:${PN}-dependencies = " \
    cairo \
    gdk-pixbuf \
    liba52 \
    libdaemon \
    libgudev \
    lame \
    libpsl \
    librsvg \
    libsoup-2.4 \
    libtheora \
    libwebp \
    mpg123 \
    orc \
    sbc \
    speex \
    taglib \
    "

RDEPENDS:${PN} = " \
    ${PN}-dependencies \
    ${PN}-basic \
    gstreamer1.0-plugins-qcom-oss-base \
    gstreamer1.0-plugins-qcom-oss-tools \
    gstreamer1.0-plugins-qcom-oss-batch \
    gstreamer1.0-plugins-qcom-oss-metamux \
    gstreamer1.0-plugins-qcom-oss-mldemux \
    gstreamer1.0-plugins-qcom-oss-mlmeta \
    gstreamer1.0-plugins-qcom-oss-mlvconverter \
    gstreamer1.0-plugins-qcom-oss-mlvclassification \
    gstreamer1.0-plugins-qcom-oss-mlvdetection \
    gstreamer1.0-plugins-qcom-oss-mlvpose \
    gstreamer1.0-plugins-qcom-oss-mlvsegmentation \
    gstreamer1.0-plugins-qcom-oss-overlay \
    gstreamer1.0-plugins-qcom-oss-qmmfsrc \
    gstreamer1.0-plugins-qcom-oss-socket \
    gstreamer1.0-plugins-qcom-oss-vcomposer \
    gstreamer1.0-plugins-qcom-oss-vsplit \
    gstreamer1.0-plugins-qcom-oss-vtransform \
    gstreamer1.0-qcom-oss-sample-apps \
    gstreamer1.0-plugins-qcom-oss-mlsnpe \
    gstreamer1.0-plugins-qcom-oss-mlqnn \
    gstreamer1.0-plugins-qcom-oss-mltflite \
    "
