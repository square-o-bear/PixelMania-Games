import pygame
from random import random

pygame.init()


class Image:
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

    def click(self):
        mouse = pygame.mouse.get_pressed()
        mouse_pos = pygame.mouse.get_pos()
        if self.rect.collidepoint(mouse_pos[0], mouse_pos[1]) and mouse[0]:
            return True
        else: return False

    def touch(self, pos, scale):
        ax1 = self.pos[0]
        ax2 = ax1 + self.scale[0]
        ay1 = self.pos[1]
        ay2 = ay1 + self.scale[1]

        bx1 = pos[0]
        bx2 = bx1 + scale[0]
        by1 = pos[1]
        by2 = by1 + scale[1]

        # print(ax1, ax2, ay1, ay2, bx1, bx2, by1, by2)

        if (((ax1 < bx1 and ax2 < bx1) or (ax1 > bx2 and ax2 > bx2))
                or ((ay1 < by1 and ay2 < by1) or (ay1 > by2 and ay2 > by2))):
            return False
        else: return True


def moveUp():
    global poel, otstup, kon_x, les_x, hero
    if poel and hero.touch((les_x, y - shir//12*25 + shir//12*16), (shir, shir//12*25)):
        les_x = int(random()*f[0]//2 + f[1] / 2 + 10 + otstup)
        kon_x = int(random()*f[0]//2 + f[1] / 2 + 10 + otstup)
        poel = False



def moveRight():
    global x
    global speed
    global storona
    storona = 0
    x += speed

def moveLeft():
    global x
    global speed
    global storona
    storona = 1
    if x-speed >= f[1] / 2 + 10 + otstup:
        x -= speed


info = pygame.display.Info()
devaiz = 0
f = (info.current_w, info.current_h)
if devaiz == 3:
    f = (2186 / 2, 1080 / 2)
win = pygame.display.set_mode(f)
doit = True
fs06 = f[1] / 6
fs03 = f[1] / 3
otstup = 12
knopkaW = Image(win, (fs06 + otstup, f[1] / 2 - fs06 * 1.5), (int(fs06), int(fs03)), 0, image="button1w.png")
knopkaS = Image(win, (fs06 + otstup, f[1] / 2 - fs06 / 2), (int(fs06), int(fs03)), 180, image="button1w.png")
knopkaD = Image(win, (fs06 + otstup, f[1] / 2 - fs06 / 2), (int(fs06), int(fs03)), -90, image="button1w.png")
knopkaA = Image(win, (otstup, f[1] / 2 - fs06 / 2), (int(fs06), int(fs03)), 90, image="button1w.png")


# --- === player === ---

speed = 10
shir = int(f[1] / 6)
x = f[0]//2
y = f[1]//2
kadr = 0
storona = 1
mon_pos = [[100, 100]]
hero = Image(win, (x, y), (shir, shir//12*16), 0, image="playr.png")
les_x = int(random()*f[0]//2 + f[1] / 2 + 10 + otstup)
kon_x = int(random()*f[0]//2 + f[1] / 2 + 10 + otstup)
if les_x >= f[0]+shir:
    les_x -= shir

poel = False
animup = True
sdvig = 0

while doit:
    pygame.time.delay(5)  # задержка
    # --- === фон === ---
    fon = Image(win, (f[1] // 2 + 10 + otstup, 0), (f[0] - f[1] // 2 + 10 + otstup, f[1]), 0, image="fon.png")
    fon.blit()
    pygame.draw.rect(win, (100, 100, 100), (0, 0, f[1] / 2 + 10 + otstup, f[1]))
    keys = pygame.key.get_pressed()

    for event in pygame.event.get():
        # exit
        if event.type == pygame.QUIT or keys[pygame.K_x]:
            doit = False

    knopkaW.blit()
    if knopkaW.click() or keys[pygame.K_w]:
        moveUp()

    knopkaD.blit()
    if knopkaD.click() or keys[pygame.K_d]:
        moveRight()

    knopkaA.blit()
    if knopkaA.click() or keys[pygame.K_a]:
        moveLeft()

    floar = Image(win, (f[1] // 2 + 10 + otstup, y - shir//12*25 + shir//12*16), (f[0] - f[1] // 2 + 10 + otstup, shir//12*25), 0, image="floar.png")
    floar.blit()

    stairs = Image(win, (les_x, y - shir//12*25 + shir//12*16), (shir, shir//12*25), 0, image="stair.png")
    stairs.blit()

    hero = Image(win, (x, y+sdvig), (shir, shir//12*16), 0, image="playr.png")
    hero.blit()

    kon = Image(win, (kon_x, y+sdvig), (shir//2, shir//2), 0, image="bonus.png")
    if not poel: kon.blit()
    if hero.touch((kon_x, y), (shir//2, shir//2)):
        poel = True

    if animup:
        sdvig += 1
        if sdvig > 5:
            animup = False
    else:
        sdvig -= 1
        if sdvig < -5:
            animup = True

    pygame.display.update()
