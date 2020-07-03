package services


import java.time.ZonedDateTime

import models.Task
import scalikejdbc.DBSession



class MockTaskService extends TaskServices {

  override def create(task:Task)(implicit session: DBSession):Long = 1L

  override def findAll(implicit s: DBSession): List[Task] =
    List(Task(Some(1l), "a", "b", Some("c"), Some("d"), ZonedDateTime.now, ZonedDateTime.now))

  override def findById(id: Long)(implicit s: DBSession): Option[Task] =
    Some(Task(Some(1l), "a", "b", Some("c"), Some("d"), ZonedDateTime.now, ZonedDateTime.now))

  override def update(id: Long, body: String, deadline: String, status: String, details: Option[String])(implicit session: DBSession): Int = 1

  override def deleteById(id: Long)(implicit s: DBSession): Int = 1
}
