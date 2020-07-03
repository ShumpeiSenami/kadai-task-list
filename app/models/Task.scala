package models

import java.time.ZonedDateTime

import scalikejdbc._, jsr310._
import skinny.orm._


/**
  * Task
  */
// body:タスク内容, deadline:タスク期限
case class Task(id:Option[Long], body:String, deadline:String, createAt:ZonedDateTime, updateAt:ZonedDateTime)

object Task extends SkinnyCRUDMapper[Task]{
  override def tableName = "tasks"

  override def defaultAlias:Alias[Task] = createAlias("m")

  override def extract(rs:WrappedResultSet, n:ResultName[Task]):Task =
    autoConstruct(rs, n)

  private def toNamedValues(record:Task):Seq[(Symbol, Any)] = Seq(
    'body     -> record.body,
    'deadline -> record.deadline,
    'createAt -> record.createAt,
    'updateAt -> record.updateAt
  )
  def create(task:Task)(implicit session:DBSession):Long =
    createWithAttributes(toNamedValues(task):_*)

  def update(task:Task)(implicit session:DBSession):Long =
    updateById(task.id.get).withAttributes(toNamedValues(task):_*)
}