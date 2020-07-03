package services

import java.time.ZonedDateTime
import javax.inject.Singleton

import models.Task
import scalikejdbc.DBSession

@Singleton
class TaskServiceImpl extends TaskServices {

  override def create(task:Task)(implicit session: DBSession):Long =
    Task.create(task)

  override def findAll(implicit s:DBSession):List[Task] = Task.findAll()

  override def findById(id:Long)(implicit s:DBSession):Option[Task] = Task.findById(id)

  override def update(id:Long, body:String, deadline:String, status:String, details:Option[String])(implicit session:DBSession):Int =
    Task
    .updateById(id)
    .withAttributes(
      'body  -> body,
      'deadline  -> deadline,
      'status    -> status,
      'details   -> details,
      'updateAt  -> ZonedDateTime.now()
    )

  override def deleteById(id:Long)(implicit s:DBSession):Int =
    Task.deleteById(id)

}
