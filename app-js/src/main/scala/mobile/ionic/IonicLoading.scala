package mobile.ionic

import com.greencatsoft.angularjs.injectable

import scala.scalajs.js

@injectable("$ionicLoading")
@js.native
trait IonicLoading extends js.Object {
  /**
    * Hides the loading indicator, if shown.
    */
  def hide(): Unit = js.native
  /**
    * Shows a loading indicator. If the indicator is already shown, it will set the options given and keep the indicator shown.
    */
  def show(opt: LoadingOpt): Unit = js.native
}

@js.native
trait LoadingOpt extends js.Object {

  /**
    * The html content of the indicator.
    */
  var template: String = js.native

  /**
    * The url of an html template to load as the content of the indicator.
    */
  var templateUrl: String = js.native

  /**
    * Whether to hide the backdrop. By default it will be shown.
    */
  var noBackdrop: Boolean = js.native

  /**
    * How many milliseconds to delay showing the indicator. By default there is no delay.
    */
  var delay: Double = js.native

  /**
    * How many milliseconds to wait until automatically hiding the indicator. By default, the indicator will be shown until .hide() is called.
    */
  var duration: Double = js.native

}



object LoadingOpt {

  /**
    * The options for the loading indicator. Available properties:
    *
    * template The html content of the indicator.
    * templateUrl The url of an html template to load as the content of the indicator.
    * noBackdrop Whether to hide the backdrop. By default it will be shown.
    * delay How many milliseconds to delay showing the indicator. By default there is no delay.
    * duration How many milliseconds to wait until automatically hiding the indicator. By default, the indicator will be shown until .hide() is called.
    *
    */
  def apply(template: String, templateUrl: String = "", noBackdrop: Boolean = false, delay: Double = 0, duration: Double = 0): LoadingOpt = {
    val opt = new js.Object().asInstanceOf[LoadingOpt]
    opt.template = template
    if (templateUrl.nonEmpty) {
      opt.templateUrl = templateUrl
    }
    opt.noBackdrop = noBackdrop
    if (delay > 0) opt.delay = delay
    if (duration > 0) opt.duration = duration
    opt
  }
}