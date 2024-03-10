DEPENDS:append:qcom = " qemu-native"
FILESEXTRAPATHS:prepend:qcom := "${THISDIR}/gstreamer1.0-plugins-good/1.20.4:"

SRC_URI:append:qcom = "\
     ${@bb.utils.contains("DISTRO_FEATURES", "pulseaudio", "file://0001-Add-pulsedirectsink-plugin.patch", "", d)} \
     file://0002-v4l2-Add-support-for-fd-memory-import.patch \
     file://0003-gstreamer1.0-plugins-good-modify-caps.patch \
    "
SRC_URI:remove:qcm6490 = "file://0001-Add-pulsedirectsink-plugin.patch"
SRC_URI:append:qcm6490 = " file://0004-v4l2-Add-support-for-dma-memory-allocation.patch"
SRC_URI:append:qcm6490 = " file://0005-v4l2-Add-support-for-dynamic-resolution-change.patch"
SRC_URI:append:qcm6490 = " file://0006-v4l2-support-for-controls-and-input-formats.patch"
