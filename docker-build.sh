#!/bin/bash
NAME=sk032
IMAGE_NAME="stock-backend"
VERSION="1.0.0"
IS_CACHE="--no-cache"

# Docker 이미지 빌드
docker buildx build \
  --tag ${NAME}-${IMAGE_NAME}:${VERSION} \
  --file Dockerfile \
  --platform linux/amd64 \
  ${IS_CACHE} .
