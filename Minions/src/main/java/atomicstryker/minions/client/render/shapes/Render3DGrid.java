package atomicstryker.minions.client.render.shapes;

import org.lwjgl.opengl.GL11;

import atomicstryker.minions.client.render.LineColor;
import atomicstryker.minions.client.render.LineInfo;
import atomicstryker.minions.common.util.Vector3;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.Vec3d;

/**
 * Draws the grid for a region between two corners in a cuboid region.
 * 
 * @author yetanotherx
 */
public class Render3DGrid
{

    protected LineColor color;
    protected Vector3 first;
    protected Vector3 second;

    public Render3DGrid(LineColor color, Vector3 first, Vector3 second)
    {
        this.color = color;
        this.first = first;
        this.second = second;
    }

    public void render(Vec3d cameraPos)
    {

        double x1 = first.getX() - cameraPos.xCoord;
        double y1 = first.getY() - cameraPos.yCoord;
        double z1 = first.getZ() - cameraPos.zCoord;
        double x2 = second.getX() - cameraPos.xCoord;
        double y2 = second.getY() - cameraPos.yCoord;
        double z2 = second.getZ() - cameraPos.zCoord;

        for (LineInfo tempColor : color.getColors())
        {
            tempColor.prepareRender();

            BufferBuilder buf = Tessellator.getInstance().getBuffer();
            buf.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);
            tempColor.prepareColor();

            double x, y, z;
            double offsetSize = 1.0;

            // Zmax XY plane, y axis
            z = z2;
            y = y1;
            int msize = 150;
            if ((y2 - y / offsetSize) < msize)
            {
                for (double yoff = 0; yoff + y <= y2; yoff += offsetSize)
                {
                    buf.pos(x1, y + yoff, z).endVertex();
                    buf.pos(x2, y + yoff, z).endVertex();
                }
            }

            // Zmin XY plane, y axis
            z = z1;
            if ((y2 - y / offsetSize) < msize)
            {
                for (double yoff = 0; yoff + y <= y2; yoff += offsetSize)
                {
                    buf.pos(x1, y + yoff, z).endVertex();
                    buf.pos(x2, y + yoff, z).endVertex();
                }
            }

            // Xmin YZ plane, y axis
            x = x1;
            if ((y2 - y / offsetSize) < msize)
            {
                for (double yoff = 0; yoff + y <= y2; yoff += offsetSize)
                {
                    buf.pos(x, y + yoff, z1).endVertex();
                    buf.pos(x, y + yoff, z2).endVertex();
                }
            }

            // Xmax YZ plane, y axis
            x = x2;
            if ((y2 - y / offsetSize) < msize)
            {
                for (double yoff = 0; yoff + y <= y2; yoff += offsetSize)
                {
                    buf.pos(x, y + yoff, z1).endVertex();
                    buf.pos(x, y + yoff, z2).endVertex();
                }
            }

            // Zmin XY plane, x axis
            x = x1;
            z = z1;
            if ((x2 - x / offsetSize) < msize)
            {
                for (double xoff = 0; xoff + x <= x2; xoff += offsetSize)
                {
                    buf.pos(x + xoff, y1, z).endVertex();
                    buf.pos(x + xoff, y2, z).endVertex();
                }
            }
            // Zmax XY plane, x axis
            z = z2;
            if ((x2 - x / offsetSize) < msize)
            {
                for (double xoff = 0; xoff + x <= x2; xoff += offsetSize)
                {
                    buf.pos(x + xoff, y1, z).endVertex();
                    buf.pos(x + xoff, y2, z).endVertex();
                }
            }
            // Ymin XZ plane, x axis
            y = y2;
            if ((x2 - x / offsetSize) < msize)
            {
                for (double xoff = 0; xoff + x <= x2; xoff += offsetSize)
                {
                    buf.pos(x + xoff, y, z1).endVertex();
                    buf.pos(x + xoff, y, z2).endVertex();
                }
            }
            // Ymax XZ plane, x axis
            y = y1;
            if ((x2 - x / offsetSize) < msize)
            {
                for (double xoff = 0; xoff + x <= x2; xoff += offsetSize)
                {
                    buf.pos(x + xoff, y, z1).endVertex();
                    buf.pos(x + xoff, y, z2).endVertex();
                }
            }

            // Ymin XZ plane, z axis
            z = z1;
            y = y1;
            if ((z2 - z / offsetSize) < msize)
            {
                for (double zoff = 0; zoff + z <= z2; zoff += offsetSize)
                {
                    buf.pos(x1, y, z + zoff).endVertex();
                    buf.pos(x2, y, z + zoff).endVertex();
                }
            }
            // Ymax XZ plane, z axis
            y = y2;
            if ((z2 - z / offsetSize) < msize)
            {
                for (double zoff = 0; zoff + z <= z2; zoff += offsetSize)
                {
                    buf.pos(x1, y, z + zoff).endVertex();
                    buf.pos(x2, y, z + zoff).endVertex();
                }
            }
            // Xmin YZ plane, z axis
            x = x2;
            if ((z2 - z / offsetSize) < msize)
            {
                for (double zoff = 0; zoff + z <= z2; zoff += offsetSize)
                {
                    buf.pos(x, y1, z + zoff).endVertex();
                    buf.pos(x, y2, z + zoff).endVertex();
                }
            }
            // Xmax YZ plane, z axis
            x = x1;
            if ((z2 - z / offsetSize) < msize)
            {
                for (double zoff = 0; zoff + z <= z2; zoff += offsetSize)
                {
                    buf.pos(x, y1, z + zoff).endVertex();
                    buf.pos(x, y2, z + zoff).endVertex();
                }
            }

            Tessellator.getInstance().draw();
        }
    }
}
