SUMMARY = "Qualcomm Gstreamer package groups"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

PROVIDES = "${PACKAGES}"
PACKAGE_ARCH = "${SOC_ARCH}"

inherit packagegroup

PACKAGES = "${PN}"

RDEPENDS:${PN}:qcom = " \
    qcom-gst-sample-apps-utils \
    qcom-gst-activate-deactivate-streams-runtime \
    qcom-gst-add-remove-streams-runtime \
    qcom-gst-add-streams-as-bundle-example \
    qcom-gst-ai-classification \
    qcom-gst-ai-daisychain-detection-classification \
    qcom-gst-ai-daisychain-detection-pose \
    qcom-gst-ai-monodepth \
    qcom-gst-ai-multi-input-output-object-detection \
    qcom-gst-ai-multistream-inference \
    qcom-gst-ai-object-detection \
    qcom-gst-ai-parallel-inference \
    qcom-gst-ai-pose-detection \
    qcom-gst-ai-segmentation \
    qcom-gst-ai-superresolution \
    qcom-gst-ai-smartcodec-example \
    qcom-gst-ai-usb-camera-app \
    qcom-gst-appsink-example \
    qcom-gst-audio-decode-example \
    qcom-gst-audio-encode-example \
    qcom-gst-audio-video-encode \
    qcom-gst-audio-video-playback \
    qcom-gst-camera-burst-capture-example \
    qcom-gst-camera-metadata-example \
    qcom-gst-camera-shdr-ldc-eis-example \
    qcom-gst-camera-single-stream-example \
    qcom-gst-camera-switch-example \
    qcom-gst-camera-per-port-example \
    qcom-gst-concurrent-videoplay-composition \
    qcom-gst-multi-camera-example \
    qcom-gst-multi-stream-example \
    qcom-gst-smartcodec-example \
    qcom-gst-snapshot-stream-example \
    qcom-gst-transform-example \
    qcom-gst-videocodec-concurrent-playback \
    qcom-gst-video-playback-example \
    qcom-gst-video-transcode-example \
    qcom-gst-webrtc-sendrecv-example \
    qcom-gst-weston-composition-example \
    qcom-gst-ai-audio-classification \
    qcom-gst-ai-multistream-batch-inference \
    qcom-gst-python-examples \
    qcom-gst-ai-face-recognition \
    qcom-gst-ai-face-detection \
    qcom-gst-ai-metadata-parser-example \
    qcom-gst-jpg-decode-example \
    qcom-gst-camera-fd-stream-example \
    qcom-gst-ai-event-encoder \
    qcom-gst-rtmp-stream-example \
    qcom-gst-vlm-app \
   "

RDEPENDS:${PN}:remove:qcs9100 = " \
    qcom-gst-camera-burst-capture-example \
    qcom-gst-camera-shdr-ldc-eis-example \
    qcom-gst-snapshot-stream-example \
  "

RDEPENDS:${PN}:remove:qcs8300 = " \
    qcom-gst-camera-burst-capture-example \
    qcom-gst-camera-shdr-ldc-eis-example \
    qcom-gst-snapshot-stream-example \
  "

RDEPENDS:${PN}:remove:qcm6490 = " \
    qcom-gst-camera-per-port-example \
  "
