package com.example

import com.example.data.ScriptEngine
import com.example.model.ScriptDuration
import com.example.model.ScriptNiche
import com.example.model.ScriptTone
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

  @Test
  fun addition_isCorrect() {
    assertEquals(4, 2 + 2)
  }

  @Test
  fun testScriptGeneratorProducesValidScenes() {
    val script = ScriptEngine.generateScript(
      niche = ScriptNiche.TECH_AI,
      duration = ScriptDuration.THIRTY,
      tone = ScriptTone.DRAMATIC
    )
    assertNotNull(script)
    assertTrue("Should contain at least 3 scenes", script.scenes.size >= 3)
    assertTrue("Hook should be populated", script.hookSpoken.isNotBlank())
    assertTrue("Seamless loop should be populated", script.loopEnding.isNotBlank())
    assertTrue("Viral score should be high", script.viralScore >= 80)
    assertTrue("Visual prompts should have 9:16 aspect ratio", script.scenes.first().visualPrompt.contains("9:16"))
  }

  @Test
  fun testTitleAnalyzerCalculatesScoresAndTriggers() {
    val result = ScriptEngine.analyzeTitle("The AI secret that scientists tried to hide")
    assertTrue(result.viralScore >= 70)
    assertTrue(result.suggestions.isNotEmpty())
    assertTrue(result.recommendedHashtags.contains("#Shorts"))
    assertTrue(result.emotionalTriggers.isNotEmpty())
  }
}
