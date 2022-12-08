package com.example.ideaconfiguration.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class OpenGitlab extends DumbAwareAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        try {
            Runtime.getRuntime()
                    .exec("/bin/bash -c ~/.local/bin/open-gitlab.sh");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
