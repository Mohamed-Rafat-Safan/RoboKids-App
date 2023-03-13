from azure.cognitiveservices.vision.computervision import ComputerVisionClient
from azure.cognitiveservices.vision.computervision.models import OperationStatusCodes
from azure.cognitiveservices.vision.computervision.models import VisualFeatureTypes
from msrest.authentication import CognitiveServicesCredentials
import os


def convertImageToText(url):
    subscription_key = "9d6e42eb7d5b497285552b371514f436"
    endpoint = "https://mo-ezz-computer-vision.cognitiveservices.azure.com/"
    computervision_client = ComputerVisionClient(endpoint, CognitiveServicesCredentials(subscription_key))

    read_response = computervision_client.read(url, raw=True)
    read_operation_location = read_response.headers["Operation-Location"]
    operation_id = read_operation_location.split("/")[-1]
    while True:
        read_result = computervision_client.get_read_result(operation_id)
        if read_result.status.lower () not in ['notstarted', 'running']:
            break

    imageText = ""
    if read_result.status == OperationStatusCodes.succeeded:
        for text_result in read_result.analyze_result.read_results:
            for line in text_result.lines:
                imageText +=  line.text

    return imageText
