package com.example.data

import com.example.model.GeneratedShortsScript
import com.example.model.ProjectStatus
import com.example.model.ScriptDuration
import com.example.model.ScriptNiche
import com.example.model.ScriptTone
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ChecklistItem(
  val id: String,
  val title: String,
  val category: String,
  val description: String,
  val isChecked: Boolean = false
)

object ProjectRepository {

  private val _projects = MutableStateFlow<List<GeneratedShortsScript>>(emptyList())
  val projects: StateFlow<List<GeneratedShortsScript>> = _projects.asStateFlow()

  private val _checklist = MutableStateFlow<List<ChecklistItem>>(defaultChecklist())
  val checklist: StateFlow<List<ChecklistItem>> = _checklist.asStateFlow()

  init {
    // Populate with 2 default starter showcase scripts
    val starter1 = ScriptEngine.generateScript(
      niche = ScriptNiche.SPACE,
      duration = ScriptDuration.THIRTY,
      tone = ScriptTone.DRAMATIC,
      customTopic = "The Great Attractor Pulling Our Galaxy"
    )
    val starter2 = ScriptEngine.generateScript(
      niche = ScriptNiche.TECH_AI,
      duration = ScriptDuration.THIRTY,
      tone = ScriptTone.MYSTERIOUS,
      customTopic = "Autonomous AI Secret Dialect"
    )
    _projects.value = listOf(starter1, starter2)
  }

  fun saveProject(script: GeneratedShortsScript) {
    _projects.update { list ->
      val index = list.indexOfFirst { it.id == script.id }
      if (index >= 0) {
        list.toMutableList().apply { set(index, script) }
      } else {
        listOf(script) + list
      }
    }
  }

  fun updateStatus(id: String, newStatus: ProjectStatus) {
    _projects.update { list ->
      list.map { if (it.id == id) it.copy(status = newStatus) else it }
    }
  }

  fun deleteProject(id: String) {
    _projects.update { list -> list.filter { it.id != id } }
  }

  fun toggleChecklistItem(id: String) {
    _checklist.update { list ->
      list.map { if (it.id == id) it.copy(isChecked = !it.isChecked) else it }
    }
  }

  fun resetChecklist() {
    _checklist.value = defaultChecklist()
  }

  private fun defaultChecklist(): List<ChecklistItem> {
    return listOf(
      ChecklistItem(
        id = "c1",
        title = "Vertical 9:16 Aspect Ratio",
        category = "Video Format",
        description = "Confirm output resolution is strictly 1080x1920 (not 16:9 or square 1:1)."
      ),
      ChecklistItem(
        id = "c2",
        title = "First 3-Second Pattern Interrupt",
        category = "Hook & Retention",
        description = "Opening scene has immediate visual motion and audio shocker with zero slow greetings."
      ),
      ChecklistItem(
        id = "c3",
        title = "Pacing Under 58 Seconds",
        category = "Audio & Timing",
        description = "YouTube Shorts are capped at 60s; 28-45s is the sweet spot for maximum replay percentage."
      ),
      ChecklistItem(
        id = "c4",
        title = "Subtitles in Central Safe Zone",
        category = "Safe Zones",
        description = "Captions are placed between 35% and 60% screen height to avoid YouTube title & button overlays."
      ),
      ChecklistItem(
        id = "c5",
        title = "Background Music Lowered to -22dB",
        category = "Audio Mixing",
        description = "AI voiceover peaks at -1.5dB so dialogue is crystal clear over the background music."
      ),
      ChecklistItem(
        id = "c6",
        title = "Seamless Audio & Visual Loop",
        category = "Retention Loop",
        description = "Final sentence connects grammatically to the opening word of the hook for endless re-watching."
      ),
      ChecklistItem(
        id = "c7",
        title = "Title Under 45 Characters & #Shorts",
        category = "YouTube SEO",
        description = "Punchy title that does not get truncated on mobile screens with #Shorts tag."
      ),
      ChecklistItem(
        id = "c8",
        title = "Pencil Thumbnail Frame Picked in Mobile App",
        category = "Publishing",
        description = "Uploaded via YouTube Mobile App and selected the most shocking frame with the pencil tool."
      )
    )
  }
}
