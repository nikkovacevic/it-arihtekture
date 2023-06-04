import AppAuth from 'remoteApp/AppAuth'
import AppApps from 'remoteApps/AppApps'
import './App.css'

function App() {

  return (
    <>
        <div style={{ height: '20%'}}>
            <AppAuth />
        </div>
        <div style={{ height: '80%'}}>
            <AppApps />
        </div>
    </>
  )
}

export default App
