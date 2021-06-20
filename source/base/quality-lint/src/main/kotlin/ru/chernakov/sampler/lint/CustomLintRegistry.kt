package ru.chernakov.sampler.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue
import ru.chernakov.sampler.lint.resources.WrongWidgetUsageDetector

@Suppress("UnstableApiUsage")
class CustomLintRegistry : IssueRegistry() {

    override val issues: List<Issue>
        get() = listOf(
            WrongWidgetUsageDetector.ISSUE
        )

    override val api: Int
        get() = CURRENT_API
}