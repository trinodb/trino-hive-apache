#!/bin/bash

set -euo pipefail
set -x

ROOT_DIR="$(dirname "${BASH_SOURCE%/*}")"

SOURCE_DIR=src/main/thrift
TARGET_DIR=target/generated-sources/thrift
rm -rf "${ROOT_DIR:?}/${TARGET_DIR}"
mkdir -p "${ROOT_DIR}/${TARGET_DIR}"
docker run -u "$(id -u)" -v "${ROOT_DIR}:/rundir" --rm thrift:0.9.3 thrift -o /rundir/${TARGET_DIR} --gen java:beans,hashcode,generated_annotations=undated /rundir/${SOURCE_DIR}/hive_metastore.thrift
mv "${ROOT_DIR}/${TARGET_DIR}"/gen-javabean/* "${ROOT_DIR}/${TARGET_DIR}"
rmdir "${ROOT_DIR}/${TARGET_DIR}"/gen-javabean
