cleari
toggleFullScreenCanvas()
setBackground(ColorMaker.rgb(255, 158, 128))
var pers = Picture.image("pers.png")
pers.scale(1.3)
pers.draw()
pers.setPosition(0, -50)
var elemetsUp: Seq[Picture] = Seq()
var elemetsDown: Seq[Picture] = Seq()
var k = 0
var GameOver = Picture.textu("Game Over")
var speed = 3
var kray = 400
var start = false
var startText = Picture.text("start - S")
startText.draw()
startText.scale(3)
startText.setPosition(-50, 300)

animate {
    if (isKeyPressed(Kc.VK_S)) {
        start = true
        startText.erase()
    }
    if (start) {
    if (isKeyPressed(Kc.VK_A) && pers.position.x > -kray) pers.offset(-5, 0)
    if (isKeyPressed(Kc.VK_D) && pers.position.x < kray) pers.offset(5, 0)
    if (isKeyPressed(Kc.VK_SHIFT)) speed = 10
    
    if (k % 20 == 0 && k > 1) {
        var e = Picture.image("puzir.png")
        e.scale(random(1, 3))
        e.draw()
        e.setPosition(random(-kray, kray), 300)
        elemetsUp = elemetsUp :+ e
    }
    if (k % 60 == 0 && k > 1) {
        var e = Picture.image("spike.png")
        e.scale(random(2, 3))
        e.draw()
        e.setPosition(random(-kray, kray), -300)
        elemetsDown = elemetsDown :+ e
    }
    for (e <- elemetsUp) {
        e.translate(0, -speed)
        if (pers.intersects(e)) {
            GameOver.draw()
            GameOver.setPosition(-350, 300)
            GameOver.scale(10)
            stopAnimations()
        }
        if (e.position.y < -500) {
            e.erase()
        }
    }
    for (e <- elemetsDown) {
        e.translate(0, speed)
        if (pers.intersects(e)) {
            GameOver.draw()
            GameOver.setPosition(-350, 300)
            GameOver.scale(10)
            stopAnimations()
        }
        if (e.position.y > 500) {
            e.erase()
        }
    }
    
    speed = 3
    k += 1
    }
}
