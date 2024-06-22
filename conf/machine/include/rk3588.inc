# Copyright (c) 2021, Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

SOC_FAMILY ?= "rk3588"
PREFERRED_VERSION_linux-rockchip ?= "6.1%"
LINUXLIBCVERSION ?= "6.1-custom%"

DEFAULTTUNE ?= "cortexa76-cortexa55-crypto"

require conf/machine/include/soc-family.inc
require conf/machine/include/arm/armv8-2a/tune-cortexa76-cortexa55.inc
require conf/machine/include/rockchip-defaults.inc
require conf/machine/include/rockchip-wic.inc

KBUILD_DEFCONFIG = "rk3588_linux_defconfig"

MALI_GPU := "valhall-g610"
MALI_VERSION ?= "g6p0"

RK_ISP_VERSION := "3.0"
