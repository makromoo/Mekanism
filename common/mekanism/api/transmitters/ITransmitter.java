package mekanism.api.transmitters;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public interface ITransmitter<N extends DynamicNetwork<?, N, D>, D> 
{
	/**
	 * Get the transmitter's transmission type
	 * 
	 * @return TransmissionType this transmitter uses
	 */
	public TransmissionType getTransmissionType();
	
	/**
	 * Gets the network currently in use by this transmitter segment.
	 * @return network this transmitter is using
	 */
	public N getTransmitterNetwork();
	
	/**
	 * Gets the network currently in use by this transmitter segment.
	 * @param createIfNull - If true, the transmitter will try and connect to an
	 * adjacent network, merging several if necessary, or creating a new one
	 * if none is available
	 * @return network this transmitter is using
	 */
	public N getTransmitterNetwork(boolean createIfNull);
	
	/**
	 * Sets this transmitter segment's network to a new value.
	 * @param network - network to set to
	 */
	public void setTransmitterNetwork(N network);
	
	/**
	 * Refreshes the transmitter's network.
	 */
	public void refreshTransmitterNetwork();
	
	/**
	 * Remove this transmitter from its network.
	 */
	public void removeFromTransmitterNetwork();

	/**
	 * Call this if you're worried a transmitter's network is messed up and you want
	 * it to try and fix itself.
	 */
	public void fixTransmitterNetwork();
	
	/**
	 * Return whether the transmitter can connect in the given direction.
	 */
	public boolean canConnect(ForgeDirection side);
	
	/**
	 * Return whether the transmitter can connect in the given direction,
	 * and the tile in the given direction isn't prevented from connecting.
	 */
	public boolean canConnectMutual(ForgeDirection side);
	
	/**
	 * Update a client-side transmitter. This tells the client how much the
	 * transmitter is transmitting.
	 * @param data Should represent what the transmitter can transmit.
	 */
	public void clientUpdate(D data);

	public boolean areTransmitterNetworksEqual(TileEntity tileEntity);
}