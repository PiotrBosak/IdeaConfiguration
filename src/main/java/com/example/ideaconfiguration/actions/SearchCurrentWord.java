package com.example.ideaconfiguration.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.DumbAwareAction;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class SearchCurrentWord extends DumbAwareAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

        var project = e.getProject();
        var editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
        var position = editor.getCaretModel().getLogicalPosition();
        var file = e.getData(PlatformDataKeys.VIRTUAL_FILE).getUrl().substring(e.getData(PlatformDataKeys.VIRTUAL_FILE).getUrl().indexOf("/Users"));
        var file2 = new java.io.File("/Users/pbk/.local/intellij-save-current-position.txt");
        try {
            var writer = new PrintWriter(file2);
            writer.write(e.getData(PlatformDataKeys.PROJECT).getBasePath() + "\n");
            writer.write(file + "\n");
            writer.write("" + position.line + "\n");
            writer.write("" + position.column + "\n");
            writer.flush();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        try {
            Runtime.getRuntime()
                    .exec("/bin/bash -c ~/.local/bin/find-current-word.sh");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
