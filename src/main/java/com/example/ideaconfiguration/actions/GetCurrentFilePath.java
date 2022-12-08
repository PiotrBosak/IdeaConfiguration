package com.example.ideaconfiguration.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.DumbAwareAction;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class GetCurrentFilePath extends DumbAwareAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        var vFile = e.getData(PlatformDataKeys.VIRTUAL_FILE);

        try {
            Runtime.getRuntime()
                    .exec(String.format("/bin/bash -c ~/.local/bin/get-current-file-path.sh  %s", vFile.getUrl()));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
