package com.azubi.tool.views.neuerbericht;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;

import java.time.Duration;

public class SendPrompt {
    private static final String API_KEY = "<you-API-key>";

    public static String send(String prompt){

        OpenAiService openAiService = new OpenAiService(API_KEY, Duration.ofSeconds(30));
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(prompt)
                .model("text-davinci-003")
                .maxTokens(1000)
                .build();
        CompletionResult completionResult = openAiService.createCompletion(completionRequest);

        if (completionResult != null) {
            String generatedText = completionResult.getChoices().get(0).getText();

            System.out.println("Generated Text: " + generatedText);
            return generatedText;
        }

        return "Fehler beim generieren deines Berichtes";

    }

}
