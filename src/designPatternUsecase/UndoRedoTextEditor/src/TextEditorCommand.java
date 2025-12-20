package designPatternUsecase.UndoRedoTextEditor.src;

public interface TextEditorCommand {
  void execute();

  void undo();
}
