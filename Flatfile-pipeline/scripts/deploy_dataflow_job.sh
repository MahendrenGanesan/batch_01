#!/usr/bin/env bash

set -e
set -o pipefail
set -u

#echo "#######Building Dataflow Docker image"

#gcloud builds submit --tag "$LOCATION-docker.pkg.dev/$PROJECT_ID/$REPO_NAME/$IMAGE_NAME:$IMAGE_TAG" .

echo "#######Creating Dataflow Flex Template"

#gcloud dataflow flex-template build "$METADATA_TEMPLATE_FILE_PATH" \
#  --image-gcr-path "$LOCATION-docker.pkg.dev/$PROJECT_ID/$REPO_NAME/$IMAGE_NAME:$IMAGE_TAG" \
#  --sdk-language "$SDK_LANGUAGE" \
#  --flex-template-base-image "$FLEX_TEMPLATE_BASE_IMAGE" \
#  --metadata-file "$METADATA_FILE" \
#  --jar "$JAR" \
#  --env FLEX_TEMPLATE_JAVA_MAIN_CLASS="$FLEX_TEMPLATE_JAVA_MAIN_CLASS"