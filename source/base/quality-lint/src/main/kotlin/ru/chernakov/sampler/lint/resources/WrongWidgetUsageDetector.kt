package ru.chernakov.sampler.lint.resources

import com.android.resources.ResourceFolderType
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.ResourceXmlDetector
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.XmlContext
import com.android.tools.lint.detector.api.XmlScannerConstants
import org.w3c.dom.Element

@Suppress("UnstableApiUsage")
class WrongWidgetUsageDetector : ResourceXmlDetector() {

    override fun appliesTo(folderType: ResourceFolderType): Boolean = ResourceFolderType.LAYOUT == folderType

    override fun getApplicableElements() = XmlScannerConstants.ALL

    override fun visitElement(context: XmlContext, element: Element) {
        if (!element.tagName.contains(CORE_UI_WIDGET_PACKAGE) && ignoredTags.contains(element.tagName).not()) {
            context.report(ISSUE, context.getLocation(element), EXPLANATION)
        }
    }

    companion object {
        private const val CORE_UI_WIDGET_PACKAGE = "ru.chernakov.sampler.widget."
        private val ignoredTags = listOf("include", "fragment", "merge", "tag", "view", "View")

        private const val DESCRIPTION = "Marks widgets that are not part of the :core-ui module."
        private const val EXPLANATION = """
            Use widgets only from the :core-ui module.
            If the module does not have a suitable widget, it must be added.
        """

        private const val ID = "WrongWidgetUsage"
        private val CATEGORY = Category.CORRECTNESS
        private const val PRIORITY = 5
        private val SEVERITY = Severity.WARNING

        @JvmStatic
        internal val ISSUE = Issue.create(
            ID,
            DESCRIPTION,
            EXPLANATION,
            CATEGORY,
            PRIORITY,
            SEVERITY,
            Implementation(
                WrongWidgetUsageDetector::class.java,
                Scope.RESOURCE_FILE_SCOPE
            )
        )
    }
}