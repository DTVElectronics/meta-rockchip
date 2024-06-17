# Copyright (C) 2021, Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

inherit auto-patch

inherit local-git

SRCREV = "9dd12da47b3063f4d5f75153a54863342f3f8ba6"
SRC_URI = " \
	git://github.com/radxa/kernel.git;protocol=https;branch=linux-5.10-gen-rkr4.1; \
"

S = "${WORKDIR}/git"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
