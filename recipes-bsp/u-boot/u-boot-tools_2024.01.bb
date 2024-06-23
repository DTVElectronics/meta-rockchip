require u-boot-common.inc
SRCREV = "866ca972d6c3cabeaf6dbac431e8e08bb30b3c8e"

SRC_URI = "git://source.denx.de/u-boot/u-boot.git;protocol=https;branch=master"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"
require recipes-bsp/u-boot/u-boot-tools.inc
