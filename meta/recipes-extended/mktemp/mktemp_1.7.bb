DESCRIPTION = "Allow safe temporary file creation from shell scripts."
HOMEPAGE = "http://www.mktemp.org/"
BUGTRACKER = "http://www.mktemp.org/bugs"
SECTION = "console/utils"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=430680f6322a1eb87199b5e01a82c0d4"

PR = "r2"

SRC_URI = "ftp://ftp.mktemp.org/pub/mktemp/${BPN}-${PV}.tar.gz \
        file://disable-strip.patch \
        file://fix-parallel-make.patch \
        "

SRC_URI[md5sum] = "787bbed9fa2ee8e7645733c0e8e65172"
SRC_URI[sha256sum] = "8e94b9e1edf866b2609545da65b627996ac5d158fda071e492bddb2f4a482675"

inherit autotools update-alternatives

EXTRA_OECONF = "--with-libc"

do_install_append () {
	mkdir ${D}${base_bindir}
	mv ${D}${bindir}/mktemp ${D}${base_bindir}/mktemp.${BPN}
	rmdir ${D}${bindir}
}

ALTERNATIVE_NAME = "mktemp"
ALTERNATIVE_LINK = "${base_bindir}/mktemp"
ALTERNATIVE_PATH = "${base_bindir}/mktemp.${BPN}"
ALTERNATIVE_PRIORITY = "100"
