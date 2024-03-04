# Copyright (c) 2024 Qualcomm Innovation Center, Inc. All rights reserved.
# SPDX-License-Identifier: BSD-3-Clause-Clear

inherit qim-prod-sdk-pkg

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

SRC_URI += "file://install.sh"

# The name and version of QIM sdk artifact
SDK_PN = "qim-prod-sdk"
PV = "1.0.0"
