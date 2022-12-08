package com.example.ideaconfiguration.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.DumbAwareAction;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class SearchCurrentWord extends DumbAwareAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

        var project = e.getProject();
        assert project != null;
        var editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
        assert editor != null;
        var position = editor.getCaretModel().getLogicalPosition();
        try {
            Runtime.getRuntime()
                    .exec(String.format("/bin/bash -c ~/.local/bin/find-current-word.sh %s %s", "" + position.line, "" + position.column));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }
}
