import os
import azure.cognitiveservices.speech as speechsdk


def recognize_from_en_audio(path):
    speech_config = speechsdk.SpeechConfig(subscription='75107d1be90b453c9d457c3fc0b5fc1d', region='eastus')
    speech_config.speech_recognition_language = "en-US"

    audio_config = speechsdk.audio.AudioConfig(filename=path)
    speech_recognizer = speechsdk.SpeechRecognizer(speech_config=speech_config, audio_config=audio_config)

    speech_recognition_result = speech_recognizer.recognize_once_async().get()

    textResult = ""

    if speech_recognition_result.reason == speechsdk.ResultReason.RecognizedSpeech:
        textResult += speech_recognition_result.text
    elif speech_recognition_result.reason == speechsdk.ResultReason.NoMatch:
        return "error"
        # print("No speech could be recognized: {}".format(speech_recognition_result.no_match_details))
    elif speech_recognition_result.reason == speechsdk.ResultReason.Canceled:
        cancellation_details = speech_recognition_result.cancellation_details
        return "error"
        # print("Speech Recognition canceled: {}".format(cancellation_details.reason))
        # if cancellation_details.reason == speechsdk.CancellationReason.Error:
        #     print("Error details: {}".format(cancellation_details.error_details))
        #     print("Did you set the speech resource key and region values?")

    return textResult
