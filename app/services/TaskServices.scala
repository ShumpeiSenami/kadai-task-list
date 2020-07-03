package services

import models.Task
import scalikejdbc.{AutoSession, DBSession}

trait TaskServices {

  // DBSessio はImplicit Contextとして暗黙的に渡せる様にする。
  // スコープに存在しない婆は、デフォルト値としてAutoSessionを利用する。
  def create(task:Task)(implicit session: DBSession = AutoSession):Long

  def findAll(implicit s: DBSession = AutoSession):List[Task]

  def findById(id:Long)(implicit s: DBSession = AutoSession): Option[Task]

  def update(id:Long, body:String, deadline:String, status:String, details:Option[String])(implicit session:DBSession = AutoSession):Int

  def deleteById(id:Long)(implicit s: DBSession = AutoSession):Int

}
