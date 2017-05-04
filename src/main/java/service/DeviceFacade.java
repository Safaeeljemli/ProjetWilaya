/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Device;
import bean.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author safa
 */
@Stateless
public class DeviceFacade extends AbstractFacade<Device> {

    @PersistenceContext(unitName = "com.mycompany_ProjetWilaya_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DeviceFacade() {
        super(Device.class);
    }
       public void verifieDeviceAndCreate(Device device) {
        List<Device> userDevices = findDeviceByUser(device.getUser());
        System.out.println(userDevices);
        if (userDevices != null) {
            for (Device userDevice : userDevices) {
                if (userDevice.getBrowser() == device.getBrowser() || userDevice.getOperatingSystem() == device.getOperatingSystem() || userDevice.getDeviceCategorie() == device.getDeviceCategorie()) {
                    return;
                }
            }
            create(device);
        }
    }

    private List<Device> findDeviceByUser(User user) {
        System.out.println("**************** findDeviceByUser ******************");
        System.out.println("USER :" + user.getLogin());
        if (user != null) {
            return getEntityManager().createQuery("SELECT d FROM Device d WHERE d.user.login='" + user.getLogin() + "'").getResultList();
        }
        return null;
    }
}
