require u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "bc-native dtc-native python3-pyelftools-native coreutils-native"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# various machines require the pyelftools library for parsing dtb files
DEPENDS:append = " python3-pyelftools-native"
DEPENDS:append:rk3308 = " u-boot-tools-native"
DEPENDS:append:rock-pi-4 = " gnutls-native"

EXTRA_OEMAKE:append:px30 = " BL31=${DEPLOY_DIR_IMAGE}/bl31-px30.elf"
EXTRA_OEMAKE:append:rk3308 = " \
	BL31=${DEPLOY_DIR_IMAGE}/bl31-rk3308.elf \
	ROCKCHIP_TPL=${DEPLOY_DIR_IMAGE}/ddr-rk3308.bin \
	"
EXTRA_OEMAKE:append:rk3328 = " BL31=${DEPLOY_DIR_IMAGE}/bl31-rk3328.elf"
EXTRA_OEMAKE:append:rk3399 = " BL31=${DEPLOY_DIR_IMAGE}/bl31-rk3399.elf"
EXTRA_OEMAKE:append:rk3566 = " \
	BL31=${DEPLOY_DIR_IMAGE}/bl31-rk3566.elf \
	ROCKCHIP_TPL=${DEPLOY_DIR_IMAGE}/ddr-rk3566.bin \
	"
EXTRA_OEMAKE:append:rk3568 = " \
	BL31=${DEPLOY_DIR_IMAGE}/bl31-rk3568.elf \
	ROCKCHIP_TPL=${DEPLOY_DIR_IMAGE}/ddr-rk3568.bin \
	"
EXTRA_OEMAKE:append:rk3588 = " \
	BL31=${DEPLOY_DIR_IMAGE}/bl31-rk3588.elf \
	ROCKCHIP_TPL=${DEPLOY_DIR_IMAGE}/ddr-rk3588.bin \
	"
EXTRA_OEMAKE:append:rk3588s = " \
	BL31=${DEPLOY_DIR_IMAGE}/bl31-rk3588.elf \
	ROCKCHIP_TPL=${DEPLOY_DIR_IMAGE}/ddr-rk3588.bin \
	"

INIT_FIRMWARE_DEPENDS ??= ""
INIT_FIRMWARE_DEPENDS:px30 = " trusted-firmware-a:do_deploy"
INIT_FIRMWARE_DEPENDS:rk3308 = " ${@bb.utils.contains('RKBIN_RK3308_LATEST', '1', 'rockchip-rkbin', 'rk3308-rkbin', d)}:do_deploy"
INIT_FIRMWARE_DEPENDS:rk3328 = " trusted-firmware-a:do_deploy"
INIT_FIRMWARE_DEPENDS:rk3399 = " trusted-firmware-a:do_deploy"
INIT_FIRMWARE_DEPENDS:rk3566 = " rockchip-rkbin:do_deploy"
INIT_FIRMWARE_DEPENDS:rk3568 = " rockchip-rkbin:do_deploy"
INIT_FIRMWARE_DEPENDS:rk3588 = " rockchip-rkbin:do_deploy"
INIT_FIRMWARE_DEPENDS:rk3588s = " rockchip-rkbin:do_deploy"
do_compile[depends] .= "${INIT_FIRMWARE_DEPENDS}"

do_compile:append:rock2-square () {
	# copy to default search path
	if [ "${SPL_BINARY}" = "u-boot-spl-dtb.bin" ]; then
		cp ${B}/spl/${SPL_BINARY} ${B}
	fi
}

do_compile:append:rk3588 () {
    oe_runmake -C ${S} O=${B}/${config} BL31=${DEPLOY_DIR_IMAGE}/bl31-rk3588.elf ROCKCHIP_TPL=${DEPLOY_DIR_IMAGE}/ddr-rk3588.bin spl/u-boot-spl.bin u-boot.dtb u-boot.itb
	./tools/mkimage -n rk3588 -T rksd -d ${DEPLOY_DIR_IMAGE}/ddr-rk3588.bin:spl/u-boot-spl.bin ${DEPLOY_DIR_IMAGE}/idbloader.img
}

do_compile:append:rk3588s () {
    oe_runmake -C ${S} O=${B}/${config} BL31=${DEPLOY_DIR_IMAGE}/bl31-rk3588.elf ROCKCHIP_TPL=${DEPLOY_DIR_IMAGE}/ddr-rk3588.bin spl/u-boot-spl.bin u-boot.dtb u-boot.itb
	./tools/mkimage -n rk3588s -T rksd -d ${DEPLOY_DIR_IMAGE}/ddr-rk3588.bin:spl/u-boot-spl.bin ${DEPLOY_DIR_IMAGE}/idbloader.img
}

