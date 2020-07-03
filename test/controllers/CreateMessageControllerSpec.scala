package controllers



import org.scalatest._
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Application
import play.api.test._
import play.api.test.Helpers._
import play.api.inject.bind
import play.api.inject.guice.GuiceApplicationBuilder
import scalikejdbc.PlayModule
import services.{TaskServices, MockTaskService}

class CreateMessageControllerSpec extends FunSpec
  with MustMatchers
  with GuiceOneAppPerSuite {

  override def fakeApplication(): Application =
    new GuiceApplicationBuilder()
    .disable[PlayModule]
    .overrides(bind[TaskServices].to[MockTaskService]) //モックに差し替える
    .build()

  describe("CreateTaskController"){
    describe("route of CreateTaskController#index"){
      it("should be valid"){
        val result = route(app, FakeRequest(GET, routes.CreateTaskController.index().toString)).get
        status(result) mustBe OK
      }
    }
    describe("route of CreateTaskController#create"){
      val result =
        route(app,
          FakeRequest(POST,routes.CreateTaskController.create().toString)
              .withFormUrlEncodedBody("body"-> "a","deadline" -> "b", "status" -> "c", "details" -> "d")).get
      status(result)mustBe SEE_OTHER
    }
  }

}
