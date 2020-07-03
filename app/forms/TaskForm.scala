package forms

case class TaskForm(id:Option[Long], body:String, deadline:String,status:String, details:String)

