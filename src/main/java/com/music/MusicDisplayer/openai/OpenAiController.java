package com.music.MusicDisplayer.openai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/openai")
public class OpenAiController {

    private final ChatClient chatClient;
    public OpenAiController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/check")
    public void OpenAiCheck(){
        String prompt = "Are you working?";
        try {
            chatClient.prompt(prompt).call().content();
            System.out.println("OpenAi is working");
        } catch (Exception e) {
            System.out.println("OpenAi is not working" + e);
        }
    }
}
