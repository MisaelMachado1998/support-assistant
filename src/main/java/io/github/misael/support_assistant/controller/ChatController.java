package io.github.misael.support_assistant.Controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    ChatController(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem("""
                        You are a technical support assistant for a software company.
                        Answer in the same language as the user.
                        Be short and clear.
                        If you don't know the answer, say you don't know. Never invent information.
                        """)
                .build();
    }

    // Low temperature: predictable, repeatable answers (support, JSON)
    @GetMapping("/chat")
    String chat(@RequestParam String message) {
        return chatClient.prompt()
                .user(message)
                .options(ChatOptions.builder().temperature(0.2))
                .call()
                .content();
    }

    // High temperature: creative, varied answers
    @GetMapping("/chat/creative")
    String creative(@RequestParam String message) {
        return chatClient.prompt()
                .user(message)
                .options(ChatOptions.builder().temperature(1.0))
                .call()
                .content();
    }
}
