
case class Section(title: String, resetLessonPosition: Boolean, position: Option[Int], lessons: List[Lesson])

case class Lesson(name: String, position: Option[Int] = None)

val sections: List[Section] = List(
  Section("Getting started", false, None, List(
    Lesson("Welcome"),
    Lesson("Installation")
  )),
  Section("Basic operator", false, None, List(
    Lesson("Addition / Subtraction"),
    Lesson("Multiplication / Division"),
  )),
  Section("Advanced topics", true, None, List(
    Lesson("Mutability"),
    Lesson("Immutability"),
  ))
)

// Without mutability
sections
  .zipWithIndex
  .map { case (section, idx) =>
    section.copy(position = Some(idx + 1))
  }
  .foldLeft((1, List.empty[Section])) { 
    case ((position, result), section) if !section.resetLessonPosition =>
      (
        position + section.lessons.size,
        result :+ section.copy(lessons = section.lessons.zipWithIndex.map { case (lesson, idx) => lesson.copy(position = Some(position + idx)) })
      )
    
    case ((_, result), section) =>
      (
        section.lessons.size,
        result :+ section.copy(lessons = section.lessons.zipWithIndex.map { case (lesson, idx) => lesson.copy(position = Some(idx + 1)) })
      )
  }
  ._2

// With mutability
var lessonPosition = 1

sections
  .zipWithIndex
  .map { case (section, idx) =>
    if (section.resetLessonPosition) lessonPosition = 1
    
    val lessons = section.lessons.zipWithIndex.map { case (lesson, idx) => lesson.copy(position = Some(lessonPosition + idx)) }
    lessonPosition += section.lessons.size
    
    section.copy(position = Some(idx + 1), lessons = lessons)
  }
