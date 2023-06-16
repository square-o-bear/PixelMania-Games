import pygame

pygame.init()


class Button:
    def __init__(self, screen, pos=(0.0, 0.0), scale=(100.0, 100.0), rot=0, image=""):
        self.screen = screen
        self.pos = pos
        self.scale = scale
        self.rotate = rot
        self.image = pygame.image.load(image)

    def blit(self):
        self.image_edited = pygame.transform.scale(self.image, self.scale)
        self.image_edited = pygame.transform.rotate(self.image_edited, self.rotate)
        self.rect = self.image_edited.get_rect(x=self.pos[0], y=self.pos[1])
        self.screen.blit(self.image_edited, self.rect)

    def touch(self, fun=exit):
        mouse = pygame.mouse.get_pressed()
        mouse_pos = pygame.mouse.get_pos()
        if self.rect.collidepoint(mouse_pos[0], mouse_pos[1]) and mouse[0]:
            fun()


class player:
    def __init__(self, screen, pos=(0, 0), scale=(100, 100), rot=0, image=""):
        self.screen = screen
        self.pos = pos
        self.scale = scale
        self.rotate = rot
        self.image = pygame.image.load(image)

    def blit(self):
        self.image_edited = pygame.transform.scale(self.image, self.scale)
        self.image_edited = pygame.transform.rotate(self.image_edited, self.rotate)
        self.rect = self.image_edited.get_rect(x=self.pos[0], y=self.pos[1])
        self.screen.blit(self.image_edited, self.rect)


def moveUp():
    global y
    global speed
    global fullscreen
    global otstup
    global storona
    global kadr
    kadr += 1
    storona = 2
    if y > 0:
        y -= speed


def moveDown():
    global y
    global speed
    global fullscreen
    global shir
    global storona
    global kadr
    kadr += 1
    storona = 3
    if y + speed + shir <= fullscreen[1]:
        y += speed


def moveRight():
    global x
    global speed
    global shir
    global storona
    global kadr
    kadr += 1
    storona = 0
    if x + speed + shir <= fullscreen[0]:
        x += speed


def moveLeft():
    global x
    global speed
    global storona
    global kadr
    kadr += 1
    storona = 1
    if x > fullscreen[0] / 2 + 10 + otstup:
        x -= speed


info = pygame.display.Info()
devaiz = 1
fullscreen = (info.current_w, info.current_h)
if devaiz == 3:
    fullscreen = (2186/2, 1080/2)
win = pygame.display.set_mode(fullscreen)
doit = True
otstup = 20
fs06 = fullscreen[0] / 6
fs03 = fullscreen[0] / 3
knopkaW = Button(win, (fs06 + otstup, fullscreen[1] / 2 - fs06 * 1.5), (int(fs06), int(fs03)), 0, image="knopka1w.png")
knopkaS = Button(win, (fs06 + otstup, fullscreen[1] / 2 - fs06 / 2), (int(fs06), int(fs03)), 180, image="knopka1w.png")
knopkaD = Button(win, (fs06 + otstup, fullscreen[1] / 2 - fs06 / 2), (int(fs06), int(fs03)), -90, image="knopka1w.png")
knopkaA = Button(win, (otstup, fullscreen[1] / 2 - fs06 / 2), (int(fs06), int(fs03)), 90, image="knopka1w.png")

# --- === player === ---

speed = 10
shir = int(fullscreen[1]/6)
x = int(fullscreen[0] / 2 - shir / 2)
y = int(fullscreen[1] / 2 - shir / 2)
kadr = 0
storona = 0

while doit:
    pygame.time.delay(30)  # задержка
    # --- === фон === ---
    pygame.draw.rect(win, (0, 220, 100), (0, 0, fullscreen[0], fullscreen[1]))
    pygame.draw.rect(win, (100, 100, 100), (0, 0, fullscreen[0] / 2 + 10 + otstup, fullscreen[1]))

    for event in pygame.event.get():
        # exit
        if event.type == pygame.QUIT:
            doit = False

    knopkaS.blit()
    knopkaS.touch(moveDown)

    knopkaW.blit()
    knopkaW.touch(moveUp)

    knopkaD.blit()
    knopkaD.touch(moveRight)

    knopkaA.blit()
    knopkaA.touch(moveLeft)

    keys = pygame.key.get_pressed()

    if keys[pygame.K_a]: moveLeft()
    if keys[pygame.K_d]: moveRight()
    if keys[pygame.K_w]: moveUp()
    if keys[pygame.K_s]: moveDown()

    if kadr >= 0:
        if storona == 0:
            hero = player(win, (x, y), (shir, shir), 0, image="pers1Right1.png")
        elif storona == 1:
            hero = player(win, (x, y), (shir, shir), 0, image="pers1Left1.png")
        elif storona == 2:
            hero = player(win, (x, y), (shir, shir), 0, image="pers1Up1.png")
        elif storona == 3:
            hero = player(win, (x, y), (shir, shir), 0, image="pers1Down1.png")

        if kadr > 7:
            kadr = -8
    else:
        if storona == 0:
            hero = player(win, (x, y), (shir, shir), 0, image="pers1Right2.png")
        elif storona == 1:
            hero = player(win, (x, y), (shir, shir), 0, image="pers1Left2.png")
        elif storona == 2:
            hero = player(win, (x, y), (shir, shir), 0, image="pers1Up2.png")
        elif storona == 3:
            hero = player(win, (x, y), (shir, shir), 0, image="pers1Down2.png")
    hero.blit()

    pygame.display.update()
