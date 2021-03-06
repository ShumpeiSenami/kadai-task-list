package controllers

import java.time.ZonedDateTime

import javax.inject._
import models.Task
import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc._
import scalikejdbc.AutoSession
import services.TaskServices



/**
  * タスク作成画面の表示
  */
@Singleton
class CreateTaskController @Inject()(
                                    components: ControllerComponents,taskServices: TaskServices
                                    )extends AbstractController(components)with I18nSupport with TaskControllerSupport {

  def index:Action[AnyContent] = Action{ implicit request =>
    Ok(views.html.create(form))
  }

  def create:Action[AnyContent] = Action{ implicit request =>
    form
      .bindFromRequest()
      .fold(
        formWithErrors => BadRequest(views.html.create(formWithErrors)),{ model =>
          // implicit val session = AutoSession 削除した
          val now = ZonedDateTime.now()
          val task = Task(None, model.body, model.deadline,Some(model.status),Some(model.details), now, now)
          val result = taskServices.create(task)
          if(result > 0){
            Redirect(routes.GetTasksController.index())
          } else {
            InternalServerError(Messages("CreateTaskError"))
          }
        }
      )
  }

}
