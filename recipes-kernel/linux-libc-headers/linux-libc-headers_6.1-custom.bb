# Copyright (C) 2021, Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

inherit auto-patch

inherit local-git

SRCREV = "3fda1bf6e05dd643bff9581ad0b0803eef4489c7"
SRC_URI = " \
	git://github.com/radxa/kernel.git;protocol=https;branch=linux-6.1-stan-rkr1; \
"

S = "${WORKDIR}/git"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
