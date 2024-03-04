# Welcome to the Qualcomm Intelligent Multimedia Product SDK (QIMP SDK)

In this documentation, you will learn:

- What is the Qualcomm Intelligent Multimedia Product SDK
- How to sync and build the Qualcomm Intelligent Multimedia Product SDK
- How to install and uninstall the Qualcomm Intelligent Multimedia Product SDK

Let's get started!

# What is the Qualcomm Product SDK

The Qualcomm Intelligent Multimedia Product SDK is a Product SDK across Internet of Things (IOT) segments encompassing QIM SDK, TF-lite SDK, SNPE SDK and QNN SDK enabling seamless multimedia and AI/ML application deployment. This SDK utilizes GStreamer, an open-source multimedia framework and exposes easy APIs and plugins in both multimedia and AI/ML domain.

The Qualcomm Intelligent Multimedia Product SDK provides advanced features as below:

- APIs and tools for multimedia/AI application development.
- Add your own application recipes based on QIMSDK for multimedia development and use AI acceleration using TFLITE-SDK/QNN/SNPE SDKs.
- Standalone compilation based on Yocto Project.

The Qualcomm Intelligent Multimedia Product SDK consist of :

- recipes: has recipes to build individual qimsdk/tflite/qnn/snpe packages
- packagegroups: has qimsdk/tflite/qnn/snpe packagegroups
- classes: has base and qimsdk/tflite/qnn/snpe packaging classes
- conf: has layer.conf with qimsdk/tflite/qnn/snpe layer specific configurations



# How to sync and build the Qualcomm Intelligent Multimedia Product SDK

## Host Setup

Refer to https://github.com/quic-yocto/qcom-manifest/blob/qcom-linux-kirkstone/README.md setup the host environment.

## Prerequisites

Run the following commands to set up Qualcomm Package Manager 3 https://qpm.qualcomm.com/:
```shell
mkdir -p <DEV_PKG_LOCATION>
cd <DEV_PKG_LOCATION>
sudo dpkg -i <downloaded Deb file>
qpm-cli --login
```

## Sync Qualcomm Intelligent Multimedia Product SDK

```shell
mkdir <QIMP SDK workspace>
cd <QIMP SDK workspace>
repo init -u https://github.com/quic-yocto/qcom-manifest -b qcom-linux-kirkstone -m qcom-6.6.13-QLI.1.0-Ver.1.2_qim-product-sdk-1.0.xml
repo sync -c -j8
```

## Build Qualcomm Intelligent Multimedia Product SDK

```shell
export SHELL=/bin/bash
MACHINE=qcm6490 DISTRO=qcom-wayland source setup-environment
bitbake qcom-multimedia-image
bitbake qim-product-sdk
```
Image output path: ${QIMP SDK workspace}/build-qcom-wayland/tmp-glibc/deploy/images/qcm6490/qcom-multimedia-image.

QIM Product SDK output path: ${QIMP SDK workspace}/build-qcom-wayland/tmp-glibc/deploy/qim_prod_sdk_artifacts.

# How to install and uninstall the Qualcomm Product SDK

## Flash image

To flash the generated build, see the [Flash software](https://docs.qualcomm.com/bundle/resource/topics/80-70014-251/flash_rb3_software_0.html)

## How to install Qualcomm Intelligent Multimedia Product SDK

```shell
adb root
adb shell mount -o remount,rw /
adb push qim-prod-sdk-rel_1.0.0.tar.gz /tmp/
adb shell
/bin/bash
cd /tmp/
tar -zxvf qim-prod-sdk-rel_1.0.0.tar.gz
cd qim-prod-sdk
chmod 777 install.sh uninstall.sh
sh install.sh
```

## How to uninstall Qualcomm Intelligent Multimedia Product SDK

```shell
adb root
adb shell mount -o remount,rw /
adb shell
/bin/bash
cd qim-prod-sdk
sh uninstall.sh
```

# Reference

[Standard Yocto environment](https://docs.yoctoproject.org/4.0.13/brief-yoctoprojectqs/index.html)

[QCOM Linux Yocto BSP releases](https://github.com/quic-yocto/qcom-manifest/blob/qcom-linux-kirkstone/README.md)
