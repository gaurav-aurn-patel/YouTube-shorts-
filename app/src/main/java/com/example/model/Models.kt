package com.example.model

enum class ScriptDuration(val seconds: Int, val label: String, val targetWords: String) {
  FIFTEEN(15, "15s (Ultra Loop)", "35-42 words"),
  THIRTY(30, "30s (Sweet Spot)", "75-85 words"),
  SIXTY(60, "60s (Deep Story)", "150-165 words")
}

enum class ScriptTone(val label: String, val emoji: String) {
  DRAMATIC("Dramatic & Suspenseful", "⚡"),
  HIGH_ENERGY("High Energy & Hype", "🔥"),
  MYSTERIOUS("Mysterious & Dark", "👁️"),
  EDUCATIONAL("Mind-Blowing & Clear", "💡"),
  CYNICAL("Hard Truth & Blunt", "🧠")
}

enum class ScriptNiche(val label: String, val emoji: String, val description: String) {
  FACTS("Mind-Blowing Facts", "🌌", "Unbelievable science, paradoxes, and bizarre realities"),
  TECH_AI("AI & Future Tech", "🤖", "Hidden AI tools, robotics, and cyber discoveries"),
  MYSTERY("Dark History & Secrets", "📜", "Unsolved crimes, forgotten secrets, ancient mysteries"),
  MONEY("Wealth & Psychology", "💰", "Money secrets, billionaire habits, economic loopholes"),
  MOTIVATION("Stoic & Self Mastery", "🦁", "Psychological power, mindset shifts, discipline"),
  SPACE("Cosmic & Ocean Depth", "🪐", "Black holes, deep ocean abysses, existential dread"),
  LIFE_HACKS("Life Hacks & Tricks", "⚡", "Productivity hacks, psychological body language tricks")
}

enum class ProjectStatus(val label: String) {
  IDEA("Idea"),
  SCRIPTED("Scripted"),
  VOICE_DONE("Voice Done"),
  VIDEO_DONE("Video Done"),
  PUBLISHED("Published")
}

data class ScriptScene(
  val sceneNumber: Int,
  val timecode: String,
  val voiceover: String,
  val visualPrompt: String,
  val visualMotion: String,
  val soundEffect: String
)

data class GeneratedShortsScript(
  val id: String = java.util.UUID.randomUUID().toString(),
  val title: String,
  val niche: ScriptNiche,
  val duration: ScriptDuration,
  val tone: ScriptTone,
  val hookVisual: String,
  val hookSpoken: String,
  val scenes: List<ScriptScene>,
  val loopEnding: String,
  val viralScore: Int,
  val viralReason: String,
  val hashtags: List<String>,
  val estimatedWpm: Int,
  val status: ProjectStatus = ProjectStatus.SCRIPTED,
  val createdAt: Long = System.currentTimeMillis()
)

data class HookTemplate(
  val id: String,
  val category: String,
  val formula: String,
  val example: String,
  val psychologicalTrigger: String,
  val retentionRate: String
)

data class AIToolInfo(
  val id: String,
  val name: String,
  val category: String, // Script, Voiceover, Visuals, Auto-Captions, All-in-One
  val badge: String,
  val rating: Float,
  val isFreeTierAvailable: Boolean,
  val description: String,
  val recommendedWorkflow: String,
  val proTip: String,
  val samplePromptOrSetting: String
)

data class GuidePhase(
  val stepNumber: Int,
  val title: String,
  val timeEstimate: String,
  val tag: String,
  val summary: String,
  val detailedExplanation: String,
  val keyFormula: String,
  val aiPrompts: List<String>,
  val recommendedTools: List<String>,
  val commonMistakes: List<String>,
  val checklistItem: String
)

data class TitleAnalysisResult(
  val originalTitle: String,
  val viralScore: Int, // 0-100
  val lengthQuality: String, // "Optimal (<50 chars)", "Too Long", etc.
  val emotionalTriggers: List<String>,
  val suggestions: List<String>,
  val recommendedHashtags: List<String>
)
