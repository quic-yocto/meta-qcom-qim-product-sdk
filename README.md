# Welcome to the Qualcomm Intelligent Multimedia Product SDK (QIMP SDK)

In this documentation, you will learn:

- What is the Qualcomm Intelligent Multimedia Product SDK
- How to sync and build the Qualcomm Intelligent Multimedia Product SDK
- How to install and uninstall the Qualcomm Intelligent Multimedia Product SDK

Let's get started!

# What is the Qualcomm Intelligent Multimedia Product SDK

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



## Prerequisites

Run the following commands to set up Qualcomm Package Manager:
```shell
sudo apt install curl
curl -L https://softwarecenter.qualcomm.com/api/download/software/qsc/linux/latest.deb -o qsc_installer.deb
sudo dpkg -i qsc_installer.deb
qpm-cli --login
```

# How to sync and build the Qualcomm Intelligent Multimedia Product SDK

## Host Setup and Download the Yocto Project BSP

Refer to https://github.com/quic-yocto/qcom-manifest/blob/qcom-linux-kirkstone/README.md setup the host environment and download Yocto Project BSP.

```shell
mkdir [release]
cd [release]
repo init -u https://github.com/quic-yocto/qcom-manifest -b [branch name] -m [release manifest]
repo sync
```

## Examples

To download the `qcom-6.6.17-QLI.1.0-Ver.1.3` release

```shell
repo init -u https://github.com/quic-yocto/qcom-manifest -b qcom-linux-kirkstone -m qcom-6.6.17-QLI.1.0-Ver.1.3.xml 
repo sync
```

## Run below command to download "meta-qcom-qim-product-sdk" layer in [release] directory where you have downloaded Yocto Project BSP.

```shell
cd [release]
git clone https://github.com/quic-yocto/meta-qcom-qim-product-sdk -b [meta-qcom-qim-product-sdk release tag] layers/meta-qcom-qim-product-sdk
```
Note: Find the latest "meta-qcom-qim-product-sdk" layer release tag names at https://github.com/quic-yocto/meta-qcom-qim-product-sdk/tags

## Examples

To download the `qcom-6.6.17-QLI.1.0-Ver.1.3_qim-product-sdk-1.1` release tag
```shell
git clone https://github.com/quic-yocto/meta-qcom-qim-product-sdk -b qcom-6.6.17-QLI.1.0-Ver.1.3_qim-product-sdk-1.1 layers/meta-qcom-qim-product-sdk 
```

## Build Yocto Project BSP plus Qualcomm Intelligent Multimedia Product SDK

```shell
export SHELL=/bin/bash
export EXTRALAYERS="meta-qcom-qim-product-sdk"
MACHINE=qcm6490 DISTRO=qcom-wayland source setup-environment
```

Run the following command to compile and generate flashable image with Yocto Project BSP plus QIM Product SDK layers
```shell
bitbake qcom-multimedia-image
```
Image output path: $[release]/build-qcom-wayland/tmp-glibc/deploy/images/qcm6490/qcom-multimedia-image.

## To generate QIM Product SDK artifacts 

```shell
bitbake qim-product-sdk    
```
QIM Product SDK output path: $[release]/build-qcom-wayland/tmp-glibc/deploy/qim_prod_sdk_artifacts.

# Flash image

To flash the generated build, see the [Flash software](https://docs.qualcomm.com/bundle/resource/topics/80-70014-251/flash_rb3_software_0.html)

# Generate Standard SDK and Extensible SDK for Standalone Application Development

	To start Developer’s application development journey, Yocto project offering two different SDK’s (Standard SDK and extensible SDK) with including cross-development tool chains, libraries, headers, and symbols specific to the image to empower the developers. The Standard SDK is suitable for straightforward cross-compilation tasks, while the eSDK extends the capabilities to more complex workflows, making it a valuable choice for developers who need additional flexibility and functionality. To generate Standard SDK and eSDK for qcom-multimedia-image, please use following commands.

Standard SDK:
```shell
bitbake -c do_populate_sdk qcom-multimedia-image
```
Standard SDK output path : [release]/build-qcom-wayland/tmp-glibc/deploy/sdk/qcom-wayland-x86_64-qcom-multimedia-image-armv8-2a-qcm6490-toolchain-1.0.sh

Extensible SDK:
```shell
bitbake -c do_populate_sdk_ext qcom-multimedia-image
```
Extensible SDK output path : [release]/build-qcom-wayland/tmp-glibc/deploy/sdk/qcom-wayland-x86_64-qcom-multimedia-image-armv8-2a-qcm6490-toolchain-ext-1.0.sh

# Reference

[Standard Yocto environment](https://docs.yoctoproject.org/4.0.13/brief-yoctoprojectqs/index.html)

[QCOM Linux Yocto BSP releases](https://github.com/quic-yocto/qcom-manifest/blob/qcom-linux-kirkstone/README.md)
