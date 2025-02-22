SUMMARY = "Simplifies building parse types based on the parse module"
HOMEPAGE = "https://github.com/jenisys/parse_type"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=16374dbaeaca1890153edb3f41371222"

SRC_URI[md5sum] = "b954062f14ab723a91fe1e2be15e859d"
SRC_URI[sha256sum] = "7f690b18d35048c15438d6d0571f9045cffbec5907e0b1ccf006f889e3a38c0b"

PYPI_PACKAGE = "parse_type"
inherit pypi ptest setuptools3

RDEPENDS:${PN} += "${PYTHON_PN}-parse ${PYTHON_PN}-six"

SRC_URI += " \
	file://run-ptest \
"

RDEPENDS:${PN}-ptest += " \
	${PYTHON_PN}-pytest \
"

do_configure:prepend() {
	sed -i -e "/python_version >= 3.0/d" ${S}/setup.py
	sed -i -e "/use_2to3/d" ${S}/setup.py
}

do_install_ptest() {
	install -d ${D}${PTEST_PATH}/tests
	cp -rf ${S}/tests/* ${D}${PTEST_PATH}/tests/
}
